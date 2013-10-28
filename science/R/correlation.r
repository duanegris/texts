# Statistics one, Lecture 6 segment 1
# Read data, plot histograms, get descriptives, examine scatterplots, run correlations

impact <- read.table("stats1_ex02.txt", header = TRUE)
hist(impact$memory.visual,xlab="Visual Memory", main="Histogram", col="red")

library(psych);
describe(impact)

# Scatterplot of two variables
plot(impact$memory.verbal ~ impact$memory.visual, main= "Scatterplot", ylab="Verbal memory",xlab="Visual Memory")
# trace the regression line (lm = linar model)
abline(lm(impact$memory.verbal ~ impact$memory.visual),col="blue")

# correlation
# One pair at a time, correlation coef only 
cor(impact$memory.verbal,impact$memory.visual)

# One pair at a time, testing for significance
cor.test(impact$memory.verbal,impact$memory.visual)

# All in a Matrix
cor(impact)


library(gclus)
impact.r = abs(cor(impact))
impact.col= dmat.color(impact.r)
impact.o <- order.single(impact.r)
# cpairs (gclus)
# draws an enhanced scatterplot matrix
# cpairs(data, order = NULL, panel.colors = NULL, border.color = "grey70", show.points = TRUE, ...)
cpairs(impact,
       impact.o, 
       panel.colors=impact.col, 
       gap=.5,
       main="Variables Ordered and Colored by Correlation")

