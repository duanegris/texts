
JAVACC=javac
JAR=jar
DIR_CLASSES=wordcount_classes  # a dir to store classes and not mix too many files with sources


all: wordcount.jar

wordcount.jar: WordCount.class 
	mkdir -p $(DIR_CLASSES)
	$(JAR) -cvf $@ -C $(DIR_CLASSES) .
	@echo "* JAR READY *"
	@echo "-> run with 'hadoop jar $@ WordCount input output' (make sure you have 'input' and 'output' does not exist yet)"

WordCount.class: WordCount.java
	hadoop com.sun.tools.javac.Main -d $(DIR_CLASSES) $< 
