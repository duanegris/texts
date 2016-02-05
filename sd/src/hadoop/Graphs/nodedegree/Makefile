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


all: nodedegree.jar

nodedegree.jar: NodeDegree.class 
	$(JAR) -cvf $@ *.class
	@echo "* JAR READY *"
	@echo "-> run with 'hadoop jar $@ NodeDegree input output' (make sure you have 'input' and 'output' does not exist yet)"

NodeDegree.class: NodeDegree.java
	export HADOOP_CLASSPATH=$(HADOOP_CLASSPATH)
	hadoop com.sun.tools.javac.Main $<
