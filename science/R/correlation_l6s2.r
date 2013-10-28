# Statistics one, Lecture 6 segment 2
# Test/re-test reliability analysis, column format

library(psych)

# read the data into a dataframe called impact.col
impact.col <- read.table("stats1_ex03.col.txt", header = TRUE)

# list the names of the variables in the dataframe
names(impact.col)

# Correlations (A&B)
cor(impact.col$memory.verbal.A,impact.col$memory.verbal.B)
cor(impact.col$memory.visual.A,impact.col$memory.visual.B)
cor(impact.col$speed.vismotor.A,impact.col$speed.vismotor.B)
cor(impact.col$speed.general.A,impact.col$speed.general.B)
cor(impact.col$impulse.control.A,impact.col$impulse.control.B)

