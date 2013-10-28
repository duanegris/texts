
daa <- read.table("DAA.01.txt",header=T)

# separate the populations depending on factor = { des, aer } 
daa_des <- daa [ daa$cond == "des", ]
daa_aer <- daa [ daa$cond == "aer", ]

# aer
layout(matrix(c(1,2,3,4,5,6,7,8), 2, 4, byrow = TRUE))
hist(daa_aer$pre.wm.s,main="AER pre spatial",xlab="score")
hist(daa_aer$post.wm.s,main="AER post spatial",xlab="score")
hist(daa_aer$pre.wm.v,main="AER pre verbal",xlab="score")
hist(daa_aer$post.wm.v,main="AER post verbal",xlab="score")

# des 
hist(daa_des$pre.wm.s,main="DES pre spatial",xlab="score")
hist(daa_des$post.wm.s,main="DES post spatial",xlab="score")
hist(daa_des$pre.wm.v,main="DES pre verbal",xlab="score")
hist(daa_des$post.wm.v,main="DES post verbal",xlab="score")
