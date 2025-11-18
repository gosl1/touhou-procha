have a stats txt file
that way we can store how many of each task category there are
then we can copy those stats to static counter variables everytime the program is loaded
since we plan on having limited tasks of each category anyway,
we can just have the data of all the tasks of category x in one file, and we can reuse that file to rebuild the task objects every run of the program
that way instead of having a file for each task, we can just have one file for each task category, then rebuild the objects like that

so the idea is that
since we cant just directly save the objects as files in java:
saving objects as a file is basically done by saving all its properties/variables/fields in a txt file
then reading that file and rebuilding the object everytime
then we save the object reference onto an array or list or something
then we use those references like tasks[i] when we need to use that specific task for something