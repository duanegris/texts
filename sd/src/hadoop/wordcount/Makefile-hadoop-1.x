
JAVACC=javac
JAR=jar
DIR_CLASSES=wordcount_classes  # a dir to store classes and not mix too many files with sources

HADOOP_LIBS=\
	${HADOOP_HOME}/hadoop-core-${HADOOP_VERSION}.jar:${HADOOP_HOME}/lib/commons-cli-1.2.jar

all: wordcount.jar

wordcount-1.x.jar: WordCount.class 
	mkdir -p $(DIR_CLASSES)
	$(JAR) -cvf $@ -C $(DIR_CLASSES) .
	@echo "* JAR READY *"
	@echo "-> run with 'hadoop jar $@ WordCount input output' (make sure you have 'input' and 'output' does not exist yet)"

WordCount.class: WordCount.java
	$(JAVACC) -classpath $(HADOOP_LIBS):. -d $(DIR_CLASSES) $<
	hadoop com.sun.tools.javac.Main WordCount.java
