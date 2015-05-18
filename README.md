# PurpleFlufferNutter

## Mobile Movie Manager

master: [![Build Status](https://travis-ci.org/OperationSleepyWeasel/PurpleFlufferNutter.svg?branch=master)](https://travis-ci.org/OperationSleepyWeasel/PurpleFlufferNutter)

develop: [![Build Status](https://travis-ci.org/OperationSleepyWeasel/PurpleFlufferNutter.svg?branch=develop)](https://travis-ci.org/OperationSleepyWeasel/PurpleFlufferNutter)

Simple application for browsing and managing your movie collection.

### Overview:
* easy browsing and filtering
* list and detailed view of the movie
* painless addition by title

### Testing under android studio
To run tests under android studio you have to enable Unit Testing support first (Settings->Gradle->Experimental). Then select **Unit Tests** from _Build variants_ panel on the left.
 To run all tests, add new JUnit run/debug configuration, select **Test kind: All in package**, and change _Working directory_ to **app** subdirectory. After saving just run it.

 *Warning:* You need to be connected to the Internet to pass tests for integration with external resources.
