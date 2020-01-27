"""""
 Page Rank
 naive implementation by S. Genaud, 2016
 based on example
 from http://www.cs.cornell.edu/home/kleinber/networks-book/networks-book-ch14.pdf

 From a matrix M representing the web pages connectivity, compute pagerank (PR) of each page.
 This is an iterative algorithm, the number k of iterations is to be set in the code.
"""


def page_rank(M, limit):
    """
    PageRank computes the rank (importance) of a page given the number of links that point to it.
    This function computes page ranks for
    :param M: the adjacency matrix : M[i][j]==1 <=> page i points to page j
    :param limit: the max number of iterations
    :return: a vector with the page rank of pages
    """
    # initially, page rank of each page is 1/npages
    npages = len(M)
    pr = [1.0 / npages] * npages
    c = [0] * npages
    recv = [0] * npages
    d = 0.85  # damping factor
    # compute number of outgoing links for page i
    for i in range(npages):
        c[i] = sum(M[i])
    print("C=", c)
    for step in range(limit):
        # for each row
        for k in range(npages):
            # compute how much page i receives from other pages
            recv[k] = 0
            for j in range(npages):
                # if not myself (but M[i][i] should be 0) and there is an ingoing link from page j
                if (k != j) and (M[j][k] == 1):
                    recv[k] += pr[j] / c[j]
                # print("+PR[",j,"]/C[",j,"]=",PR[j],"/",C[j],"=",recv[i])
                # print("recv[",i,"]=",recv[i])
        # now update PRs
        for k in range(npages):
            # PR[i] = recv[i]  # no damping  (All values sum to 1)
            pr[k] = (1 - d) + d * recv[k]  # damping
    return pr


if __name__ == '__main__':

    # Adjacency matrix representing the link between pages
    # M(i,j)=1 <=> P_i has an outgoing link to P_j
    # np.array(  # this would be for use with numpy
    M = [[0, 1, 1, 0, 0, 0, 0, 0],
         [0, 0, 0, 1, 1, 0, 0, 0],
         [0, 0, 0, 0, 0, 1, 1, 0],
         [1, 0, 0, 0, 0, 0, 0, 1],
         [1, 0, 0, 0, 0, 0, 0, 1],
         [1, 0, 0, 0, 0, 0, 0, 0],
         [1, 0, 0, 0, 0, 0, 0, 0],
         [1, 0, 0, 0, 0, 0, 0, 0]]
    # )
    limit = 5  # max number of iterations
    PR = page_rank(M, limit)
    # print results
    for (i, elem) in enumerate(PR):
        print("PR_"+str(i)+"=", elem)
