##
## Files
##

TEX_SOURCE =gp.tex
TEX_DEPEND = contexte.tex decoupage.tex estimation.tex planification.tex
PDF_TARGET = $(TEX_SOURCE:%.tex=%.pdf)

FIG_FILES = $(wildcard *.fig)

PDFTEX_FILES = $(FIG_FILES:%.fig=%.pdftex)
PDFTEX_T_FILES = $(FIG_FILES:%.fig=%.pdftex_t)


##
## Programs
##

FIG2DEV = fig2dev
EPSTOPDF = epstopdf

BIBTEX = bibtex
LATEX = latex #--interaction nonstopmode
PDFLATEX = pdflatex --interaction nonstopmode

# -Ppdf : to use Type1 fonts
# -G0 : to workaround a ligature bug
DVIPS = dvips  -ta4 -Ppdf -G0

PS2PDF = ps2pdf -dAutoRotatePages=/All \
	 	-dMaxSubsetPct=100 -dCompatibilityLevel=1.2 \
		-dSubsetFonts=true -dEmbedAllFonts=true \
		-dAutoFilterColorImages=false \
		-dAutoFilterGrayImages=false \
		-dColorImageFilter=/FlateEncode \
		-dGrayImageFilter=/FlateEncode \
		-dModoImageFilter=/FlateEncode 

RM = rm -f
CP = cp -f

##
## Specific rules
##

.PHONY: all clean realclean 

all: $(PDF_TARGET) 

clean:
	-$(RM) *.{aux,log,bbl,blg,pdftex,pdftex_t} *~ *.bak $(IMAGE_FILES) *.eps *.out *.snm
	-$(RM) $(PSTEX_T_FILES) $(EPS_FILES) $(PDF_FILES)

realclean: clean
	-$(RM) $(PDF_TARGET)
#	$(RM) -fr auto/

##
## Dependencies
##

$(PDF_TARGET): $(PDFTEX_T_FILES) $(PDFTEX_FILES) $(PDF_FILES) $(TEX_DEPEND)

##
## Generic rules
##
%.pdf: %.tex
	$(PDFLATEX) $(TEX_SOURCE)


%.pdftex: %.fig
	$(FIG2DEV) -L pdftex -p $* $< $@

%.pdftex_t: %.fig
	$(FIG2DEV) -L pdftex_t -p $*.pdftex $< $@

handout:
	pdftops -nocenter gp.pdf gp.ps
	psnup -4 -W128mm -H96mm -pa4 gp.ps gp-4pp.ps
	ps2pdf gp-4pp.ps gp-4pp.pdf

correct_fig:
	sed -i "s/epsfig{file=/includegraphics{/g" *.pdftex_t
