cmake_minimum_required(VERSION 3.22)

# macOS platform environment variables
set(CMAKE_OSX_DEPLOYMENT_TARGET "10.10")
# set(CMAKE_OSX_ARCHITECTURES "x86_64;i386;arm64")

project(hydrogen-deps)
include(ExternalProject)




set(CMAKE_INSTALL_PREFIX ${CMAKE_CURRENT_SOURCE_DIR}/hydrogen-deps)

message("=== libarchive ===")
# disable compression methods that Hydrogen
# doesn't use, to reduce dependencies on non-system libraries
set(ENABLE_LZ4 OFF CACHE BOOL "Enable LZ4 compression")
set(ENABLE_LZMA OFF CACHE BOOL "Enable lzma compression")
set(ENABLE_ZSTD OFF CACHE BOOL "Enable zstd compression")
set(ENABLE_BZip2 OFF CACHE BOOL "Enable bzip2 compression")
set(ENABLE_LIBB2 OFF CACHE BOOL "Enable libb2")
# set(ENABLE_LIBXML2 OFF CACHE BOOL "")
add_subdirectory(sources/libarchive)

message("=== libsndfile ===")
add_subdirectory(sources/libsndfile)

#message("XXX FFTW3")
#
#ExternalProject_Add(fftw3
#                    URL https://www.fftw.org/fftw-3.3.10.tar.gz)

message("=== libsamplerate ===")
set(BUILD_TESTING OFF)
add_subdirectory(sources/libsamplerate)

message("=== aften ===")
# Aften CMake is old and crufty so treat as an external project
ExternalProject_Add(aften
                    SOURCE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/sources/aften-0.0.8
                    CONFIGURE_COMMAND cmake <SOURCE_DIR> -DCMAKE_INSTALL_PREFIX=${CMAKE_INSTALL_PREFIX}
                        -DSHARED=ON
                    INSTALL_COMMAND cmake --build . --target install)


# Berkeley DB
message("=== Berkeley DB ===")
ExternalProject_Add(berkeleydb
                    # PREFIX ${CMAKE_CURRENT_SOURCE_DIR}/sources/db-18.1.40
                    SOURCE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/sources/db-18.1.40
                    CONFIGURE_COMMAND <SOURCE_DIR>/dist/configure --prefix ${CMAKE_INSTALL_PREFIX}
                    BUILD_COMMAND make
                    INSTALL_COMMAND
                      # Missing doc components: create empty files so build succeeds
                      touch bdb-sql gsg_db_server ;
                      make install
                   )

# JACK
message("=== JACK ===")
set(CXXFLAGS "-I${CMAKE_INSTALL_PREFIX}/include")
set(LDFLAGS "-L${CMAKE_INSTALL_PREFIX}/lib")
ExternalProject_Add(jack2
                    # SOURCE_DIR sources/jack2
                    GIT_REPOSITORY https://github.com/jackaudio/jack2.git
                    GIT_TAG v1.9.21
                    BUILD_IN_SOURCE ON
                    CONFIGURE_COMMAND CXXFLAGS=${CXXFLAGS} LDFLAGS=${LDFLAGS} python3 ./waf configure
                                        --prefix ${CMAKE_INSTALL_PREFIX}
                    BUILD_COMMAND python3 ./waf build
                    INSTALL_COMMAND python3 ./waf install)
