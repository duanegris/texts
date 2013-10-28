##
## Files
##

TEX_SOURCE = p2pmpi.tex
PDF_TARGET = $(TEX_SOURCE:%.tex=%.pdf)

FIG_FILES = $(wildcard *.fig)

PDFTEX_FILES = $(FIG_FILES:%.fig=%.pdftex)
PDFTEX_T_FILES = $(FIG_FILES:%.fig=%.pdftex_t)
#IMAGE_FILES=is.pdf ep.pdf is_rep.pdf ray_bench.pdf


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

all: $(IMAGE_FILES) $(PDF_TARGET) 

clean:
	-$(RM) *.{nav,out,snm,toc,aux,log,bbl,blg,pdftex,pdftex_t} *~ *.bak $(IMAGE_FILES) *.eps
	-$(RM) $(PSTEX_T_FILES) $(EPS_FILES) $(PDF_FILES)

realclean: clean
	-$(RM) $(PDF_TARGET)
#	$(RM) -fr auto/

##
## Dependencies
##

$(PDF_TARGET): $(PDFTEX_T_FILES) $(PDFTEX_FILES) $(PDF_FILES)

##
## Generic rules
##
is.pdf : is.dat
	        ./is_plot.sh is.dat
		$(EPSTOPDF) is.eps

ep.pdf : ep.dat
	        ./ep_plot.sh ep.dat
		$(EPSTOPDF) ep.eps

is_rep.pdf : is_rep.dat
	        ./replica_plot.sh is_rep.dat
		$(EPSTOPDF) is_rep.eps

ray_bench.pdf : ray_bench.dat
	        ./ray_plot.sh ray_bench.dat
		$(EPSTOPDF) ray_bench.eps

%.pdf: %.tex
	$(PDFLATEX) $(TEX_SOURCE)


%.pdftex: %.fig
	$(FIG2DEV) -L pdftex -p $* $< $@

%.pdftex_t: %.fig
	$(FIG2DEV) -L pdftex_t -p $*.pdftex $< $@

