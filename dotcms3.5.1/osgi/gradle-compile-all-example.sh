#! /bin/bash

## Assumes gradle binaries are available in PATH.

## Run from the context of the folder that contains all your OSGI plugin root directories.
## I typically add an /osgi folder to the dotCMS root folder.
## (Or optionally navigate there now.)
#cd /some/path/to/my/dotCMS/osgi

## For each sub-directory which matches org.example.nkeiter*
## (Change the filter if needed.)
for d in org.example.nkeiter*/ ; do 

	pushd $d

	## If the sub-directory contains a build.gradle file.
	if [ -f build.gradle ];

	then

		echo "Running gradle-3.0 compiler..."

		## Run the gradle compiler.
		gradle jar --stacktrace

		echo "Finished running gradle-3.0 compiler..."

	fi

	popd

done
