# Statistics one, Lecture 12
# Mediation analysis
# X is extraversion
# Y is hapiness
# M is diversity of life experience


library(psych)

# read the data into a dataframe called impact.col
med <- read.table("stats1_ex05.txt", header = TRUE)

# Print descriptive stat and histograms to test for
# univariate normal assumptions
describe(med)
hist(med$happy)
hist(med$extra)
hist(med$diverse)

model1=lm( med$happy ~ med$extra )
model2=lm( med$diverse~ med$extra )
model3=lm( med$happy ~ med$extra + med$diverse )
summary(model1)
summary(model2)
summary(model3)

# Correlations (A&B)
cor(impact.col$memory.verbal.A,impact.col$memory.verbal.B)
cor(impact.col$memory.visual.A,impact.col$memory.visual.B)
cor(impact.col$speed.vismotor.A,impact.col$speed.vismotor.B)
cor(impact.col$speed.general.A,impact.col$speed.general.B)
cor(impact.col$impulse.control.A,impact.col$impulse.control.B)

