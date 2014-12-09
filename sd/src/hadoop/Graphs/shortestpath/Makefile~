
JAVACC=javac
JAR=jar

DIR_CLASSES=degree_classes  # a dir to store classes and not mix too many files with sources
JAR_FILE=degree.jar
MAIN=NodeDegree

HADOOP_LIBS=\
	${HADOOP_HOME}/hadoop-core-${HADOOP_VERSION}.jar:${HADOOP_HOME}/lib/commons-cli-1.2.jar

all: $(JAR_FILE)

$(JAR_FILE): $(MAIN).class 
	mkdir -p $(DIR_CLASSES)
	$(JAR) -cvf $@ -C $(DIR_CLASSES) .
	@echo "* JAR READY *"
	@echo "-> run with 'hadoop jar $@ $(MAIN) input output' (make sure you have 'input' and 'output' does not exist yet)"

$(MAIN).class: $(MAIN).java
	mkdir -p $(DIR_CLASSES)
	$(JAVACC) -classpath $(HADOOP_LIBS):. -d $(DIR_CLASSES) $<
