# Statistics one, Lecture 9
# Multiple regression analysis

library(psych)

# read the data into a dataframe
endur <- read.table("stats1_ex04.txt", header = TRUE)

# compare enurance and age ...
plot(endur$endurance ~ endur$age, main="Scatterplot",ylab="Endurance", xlab="Age")
abline(lm(endur$endurance ~ endur$age),col="blue")
# -> the slope is negative: Endurance gets negatively correlated with Age
# as age grows



# compare enurance and activeyears ...
plot(endur$endurance ~ endur$activeyears, main="Scatterplot",ylab="Endurance", xlab="Active Years")
abline(lm(endur$endurance ~ endur$activeyears),col="red")
# -> expectedly, the slope is positive: Endurance is correlated with active years


# simple regression, outcome Y=endurance
#  1) Y ~age
#  2) Y ~activeyears

model1=lm(endur$endurance ~ endur$age)
summary(model1)

model2=lm(endur$endurance ~ endur$activeyears)
summary(model2)

# Now MR ...
model3 = lm ( endur$endurance ~ endur$age+ endur$activeyears )
summary(model3)


# Normalize : scale()
model3.z = lm(formula = scale(endur$endurance) ~ scale(endur$age) + scale(endur$activeyears))
summary(model3.z)
