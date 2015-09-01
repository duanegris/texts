#
# 
# Makefile for Hadoop 2.x
#
# ------------------------------------------------------------------------------
# Take care of setting the $JAVA_HOME and $HADOOP_CLASS in your .bashrcrc or equivalent,
# as examplified below:
#
# On MacOSX Yosemite a convenient way of setting JAVA_HOME is 
# export JAVA_HOME=`/usr/libexec/java_home -v 1.8`


# export HADOOP_VERSION=2.4.0
# export HADOOP_HOME=/usr/local/Cellar/hadoop/${HADOOP_VERSION}   # from HomeBrew
# export PATH=$HADOOP_HOME/bin:$PATH
# export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar
# ------------------------------------------------------------------------------


PROJECT=SplitRead
JAVACC=javac
JAR=jar



all: $(PROJECT).jar

$(PROJECT).jar : $(PROJECT).class 
	$(JAR) -cvf $@ $<
	@echo "** JAR READY **"
	@echo "-> Make sure hadoop can reach your $(PROJECT).jar file." 
	@echo '   e.g if in the current directory, export HADOOP_CLASSPATH=$$HADOOP_CLASSPATH:.'
	@echo "-> Run :"
	@echo "     hadoop jar $@ $(PROJECT) input output"
	@echo "(make sure you have 'input' and 'output' does not exist yet)"

$(PROJECT).class : $(PROJECT).java 
	hadoop com.sun.tools.javac.Main $< 

clean:
	rm -f $(PROJECT).{jar,class}
