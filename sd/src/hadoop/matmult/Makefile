#
# 
# Makefile for Hadoop 2.x
#
# ------------------------------------------------------------------------------
# Take care of setting the $JAVA_HOME and $HADOOP_CLASS as examplified below
#
# On MacOSX Yosemite a convenient way of setting JAVA_HOME is 
# export JAVA_HOME=`/usr/libexec/java_home -v 1.8`


# export HADOOP_VERSION=2.4.0
# export HADOOP_HOME=/usr/local/Cellar/hadoop/${HADOOP_VERSION}   # from HomeBrew
# export PATH=$HADOOP_HOME/bin:$PATH
# export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar
# ------------------------------------------------------------------------------



JAVACC=javac
JAR=jar
DIR_CLASSES=mm_classes  # a dir to store classes and not mix too many files with sources


all: mm.jar

mm.jar: Mm.class 
	$(JAR) -cvf $@ -C $(DIR_CLASSES) .
	@echo "* JAR READY *"
	@echo "-> run with 'hadoop jar $@ Mm file_in dir_out" 

Mm.class: Mm.java
	mkdir -p $(DIR_CLASSES)
	hadoop com.sun.tools.javac.Main -d $(DIR_CLASSES) $< 
