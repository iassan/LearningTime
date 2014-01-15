all: clean
	pdflatex show.tex
	pdflatex show.tex
	thumbpdf show
	pdflatex show.tex
	mv show.pdf ErrorsHandlingInScala.pdf

clean:
	rm -rf *.aux *.log *.nav *.aut *.snm *.toc *.tpt *.out

distclean: clean
	rm -rf show.pdf ErrorsHandlingInScala.pdf
