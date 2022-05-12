cmake_minimum_required(VERSION 3.22)
project(hydrogen-deps)
include(ExternalProject)

# macOS platform environment variables
# set(ENV{MACOSX_DEPLOYMENT_TARGET} 10.10)
set(CMAKE_OSX_DEPLOYMENT_TARGET "10.10")
# set(CMAKE_OSX_ARCHITECTURES "x86_64;i386;arm64")

# libarchive options: disable compression methods that Hydrogen
# doesn't use, to reduce dependencies on non-system libraries
set(ENABLE_LZ4 OFF CACHE BOOL "Enable LZ4 compression")
set(ENABLE_LZMA OFF CACHE BOOL "Enable lzma compression")
set(ENABLE_ZSTD OFF CACHE BOOL "Enable zstd compression")
set(ENABLE_BZip2 OFF CACHE BOOL "Enable bzip2 compression")
set(ENABLE_LIBB2 OFF CACHE BOOL "Enable libb2")
# set(ENABLE_LIBXML2 OFF CACHE BOOL "")



set(CMAKE_INSTALL_PREFIX ${CMAKE_CURRENT_SOURCE_DIR}/hydrogen-deps)

message("XXX LibArchive")
add_subdirectory(sources/libarchive)
add_subdirectory(sources/libsndfile)

#message("XXX FFTW3")
#
#ExternalProject_Add(fftw3
#                    URL https://www.fftw.org/fftw-3.3.10.tar.gz)

message("XXX libsamplerate")
set(BUILD_TESTING OFF)
add_subdirectory(sources/libsamplerate)

ExternalProject_Add(berkeleydb
                    # PREFIX ${CMAKE_CURRENT_SOURCE_DIR}/sources/db-18.1.40
                    SOURCE_DIR ${CMAKE_CURRENT_SOURCE_DIR}/sources/db-18.1.40
                    CONFIGURE_COMMAND <SOURCE_DIR>/dist/configure --prefix ${CMAKE_INSTALL_PREFIX}
                    BUILD_COMMAND make
                    INSTALL_COMMAND touch bdb-sql gsg_db_server; make install
                   )

# JACK
ExternalProject_Add(jack2
                    # SOURCE_DIR sources/jack2
                    GIT_REPOSITORY https://github.com/jackaudio/jack2.git
                    GIT_TAG v1.9.21
                    BUILD_IN_SOURCE ON
                    CONFIGURE_COMMAND python3 ./waf configure
                                        --prefix ${CMAKE_INSTALL_PREFIX}
                    BUILD_COMMAND python3 ./waf build
                    INSTALL_COMMAND python3 ./waf install)
