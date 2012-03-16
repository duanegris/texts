-include /opt/local/include/LaTeX.mk
-include /usr/include/LaTeX.mk
-include /usr/local/include/LaTeX.mk

all: pr_451.pdf

pr_451.fb.bbl : pr_451.tex generalbiblio.bib
	@echo "@@@@@@@@@@@@@@@@@"
	bibtex pr_451.fb
	bibtex pr_451
