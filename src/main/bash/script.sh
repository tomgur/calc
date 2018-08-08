#!/bin/bash

##########################################################################################
##########################################################################################
######## I couldn't, for the life of me, get the regex negative lookahead to     #########
######## work in BASH. :( - I spent the better part of a day to get it to work   #########
######## but, to no avail. This way works just as well - would get a unique list #########
######## of status codes before running and use that as parameters.              #########
######## Creates 2 files:                                                        #########
########      clientsSortedByPageViews                                           #########
########      relocatedPages                                                     #########
##########################################################################################
##########################################################################################

rm -f clientsSortedByPageViews
rm -f relocatedPages
echo "Starting process - "`date`

cat $1 | grep -E "^.*\[(.*)\]\s\"(.*)\sHTTP\/1\.0\"\s200\s\d*"|cut -d" " -f1|uniq -c|sort -rn > clientsSortedByPageViews

cat $1 | grep -E "^.*\[(.*)\]\s\"(.*)\sHTTP\/1\.0\"\s302\s\d*" >> relocatedPages
cat $1 | grep -E "^.*\[(.*)\]\s\"(.*)\sHTTP\/1\.0\"\s304\s\d*" >> relocatedPages
cat $1 | grep -E "^.*\[(.*)\]\s\"(.*)\sHTTP\/1\.0\"\s404\s\d*" >> relocatedPages

echo "Finished process - "`date`
