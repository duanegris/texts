base=`basename $1 .pdf`
tmp_ps="${base}.ps"
tmp_4ps="${base}-4pp.ps"
pdftops -expand -pagecrop  -duplex -level3  $1 ${tmp_ps}
psnup -pa4 -n 4 -d ${tmp_ps} ${tmp_4ps}
ps2pdf  ${tmp_4ps} ${base}-4pp.pdf
rm -f ${tmp_ps} ${tmp_4ps}

