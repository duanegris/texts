# Course 'Statistics One', 1st example script
# Plot histograms of file 'stats1_ex01.txt' as a table layout and 
# prints out all the descriptive statistics

   ratings <- read.table("stats1_ex01.txt",header = T) # 1st line = row names

   # --> plots histo: c=concatenate
   layout(matrix(c(1,2,3,4), 2, 2, byrow = TRUE))
   hist(ratings$RedTruck, xlab = "Ratings", ylab="Number", main="RedTruck")
   hist(ratings$HobNob, xlab = "Ratings", ylab="Number", main="HobNob")
   hist(ratings$FourPlay, xlab = "Ratings", ylab="Number", main="FourPlay")
   hist(ratings$WoopWoop, xlab = "Ratings", ylab="Number", main="WoopWoop")
   
   describe(ratings)  # from the 'psych' package, 
   # An alternative is to use 'summary(ratings)'
