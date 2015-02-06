My program loops through each word in the dictionary (of words that are the same size as the input words) 
and for each word checks if all the letters except one in that word equal one of the words in the "nextWords" list which 
is equal to the new words that are being generated. Each time after it loops through
all the words on the nextWords list it adds each new word to a list called "temp"
and then puts the words from temp onto nextWords and then clears temp. Also, as it loops
throught nextWords, each time it finds that a word in the dictionary equals a word on 
nextWords except for one letter, it adds that word and the original word that it derived
from onto a map called "prevWords". If the endWord is found in nextWords then it prints out the edit
distance and the chain from the first word to the end word using recursion and the
program ends. If nextWords is found to be empty it print out that the edit distance between
the start word and the end word is undefined.