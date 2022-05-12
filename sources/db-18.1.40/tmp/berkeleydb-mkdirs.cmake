# Distributed under the OSI-approved BSD 3-Clause License.  See accompanying
# file Copyright.txt or https://cmake.org/licensing for details.

cmake_minimum_required(VERSION 3.5)

file(MAKE_DIRECTORY
  "/Users/colin/Sync/hydrogen-deps/build/<PREFIX>"
  "/Users/colin/Sync/hydrogen-deps/sources/db-18.1.40/src/berkeleydb-build"
  "/Users/colin/Sync/hydrogen-deps/sources/db-18.1.40"
  "/Users/colin/Sync/hydrogen-deps/sources/db-18.1.40/tmp"
  "/Users/colin/Sync/hydrogen-deps/sources/db-18.1.40/src/berkeleydb-stamp"
  "/Users/colin/Sync/hydrogen-deps/sources/db-18.1.40/src"
  "/Users/colin/Sync/hydrogen-deps/sources/db-18.1.40/src/berkeleydb-stamp"
)

set(configSubDirs )
foreach(subDir IN LISTS configSubDirs)
    file(MAKE_DIRECTORY "/Users/colin/Sync/hydrogen-deps/sources/db-18.1.40/src/berkeleydb-stamp/${subDir}")
endforeach()
