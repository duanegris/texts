#!/bin/bash

function usage () {
echo "usage: $0 -n number_of_layouts_per_page -i pdf_file"
exit 1
}

while getopts "n:i:" Option
do
   case $Option in
      n) page_per_layout=$OPTARG;;
      i) input=$OPTARG;; 
      *) echo "illegal option";usage;;
    esac
done 
shift $(($OPTIND - 1))
echo $#
if [ $# != 0 ]; then
      usage
fi

base="`basename ${input} .pdf`"
tmp_ps="${base}.ps"
tmp_4ps="${base}-4pp.ps"
pdftops -expand -pagecrop  -duplex -level3 ${input} ${tmp_ps}
psnup -pa4 -n 4 -d ${tmp_ps} ${tmp_4ps}
ps2pdf  ${tmp_4ps} ${base}-4pp.pdf
rm -f ${tmp_ps} ${tmp_4ps}

