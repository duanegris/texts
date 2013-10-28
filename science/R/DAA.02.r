
daa <- read.table("DAA.02.txt",header=T)

# separate the populations depending on factor = { des, aer } 
daa_des <- daa [ daa$cond == "des", ]
daa_aer <- daa [ daa$cond == "aer", ]

library(psych)
describe(daa_des)
describe(daa_aer)

# correlation matrix for each condition. 
# Remove 2 first cols "pid" and "cond" that are non numerical 
des <- daa_des[3:10]
aer <- daa_aer[3:10]
cor(des)
cor(aer)

# The textual matrices above are enough to compare each variable.
# The nice graphics generated below do not have a real added value
# because they are difficult to read.


library(gclus)
aer.r = abs(cor(aer))
aer.color = dmat.color(aer.r)
aer.o <- order.single(aer.r)
# cpairs (gclus)
# draws an enhanced scatterplot matrix
# cpairs(data, order = NULL, panel.colors = NULL, border.color = "grey70", show.points = TRUE, ...)
cpairs(aer,
       aer.o,
       panel.colors=aer.color,
       gap=.5,
       main="Variables Ordered and Colored by Correlation")

