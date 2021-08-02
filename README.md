# Usage
## Launch Stable Version
```bash
java -jar logger.jar --help

```

```bash
usage: LogApp [-h] [-f FILE] [-d DATABASE]

Log events from a given file  to  a  database. Flags them if their duration
is greater than 5ms.

named arguments:
  -h, --help             show this help message and exit
  -f FILE, --file FILE   The input file to compute (default: input.txt)    
  -d DATABASE, --database DATABASE
                         The  database  where  the   processed  events  are
                         stored (default: logDatabase)
```
## Launch Tests

```bash
java -jar logger.jar --help
```

*Note that logs are enabled for tests and that as some tests lead to errors, error messages may be displayed during test execution. Only the number of successful tests matters.*

# Coding Standards
## Architecture

**The project will respect the layered architecture principle.**

```
             Entities            Models
Controllers <--------> Services <------> Repository 
```

## File Construction

* Every File must go through the following steps:
    * **EMPTY** : The file is empty or contains an empty class.
    * **PROTOTYPES** : The class contains its major prototypes (start from controllers, then down to repositories).
    * **DOCUMENTATION** : Every prototype is well documented.
    * **UNSTABLE** : The class methods are being implemented and are not functional yet.
    * **STABLE** : All class methods are fully implemented and there is no pre-compilation errors.
    * **TESTED** : All methods from the class were tested.
    * **FINISHED** : The file is in its final state.
* The state of the file should be written as a comment on the top of it

## Commit

* **Commit should be frequent** and follow the given standard:

[FILENAME][STATE] description```

were *STATE* is the state of the file (see above) and description a short description of what have been done.
 and *FILENAME* the name of the file which have been modified.

* Each commit should concern **only one file**.

--

*Author : Henri Jamet, 30/07/2021*