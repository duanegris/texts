/* uncompress.c */


#include "compress.h"
/************************************************************************/
void uncompress(p_src_first,src_len,p_dst_first,p_dst_len)
	  /* Input : Specify input block using p_src_first and src_len. */
	  /* Input : Point p_dst_first to the start of the output zone. */
	  /* Input : Point p_dst_len to a ULONG to receive the output length. */
	  /* Input : Input block and output zone must not overlap. User knows */
	  /* Input : upperbound on output block length from earlier compression. */
	  /* Input : In any case, maximum expansion possible is eight times. */
	  /* Output : Length of output block written to *p_dst_len. */
	  /* Output : Output block in Mem[p_dst_first..p_dst_first+*p_dst_len-1]. */
	  /* Output : Writes only in Mem[p_dst_first..p_dst_first+*p_dst_len-1]. */
	  UBYTE *p_src_first, *p_dst_first; ULONG src_len, *p_dst_len;
{UWORD controlbits=0, control;
	  UBYTE *p_src=p_src_first+FLAG_BYTES, *p_dst=p_dst_first,
		  *p_src_post=p_src_first+src_len;
	  if (*p_src_first==FLAG_COPY)
	  {fast_copy(p_src_first+FLAG_BYTES,p_dst_first,src_len-FLAG_BYTES);
		    *p_dst_len=src_len-FLAG_BYTES; return;}
		    while (p_src!=p_src_post)
		    {if (controlbits==0)
				{control=*p_src++; control|=(*p_src++)<<8; controlbits=16;}
				if (control&1)
				{UWORD offset,len; UBYTE *p;
					  offset=(*p_src&0xF0)<<4; len=1+(*p_src++&0xF);
					  offset+=*p_src++&0xFF; p=p_dst-offset;
					  while (len--) *p_dst++=*p++;}
				else
					  *p_dst++=*p_src++;
				control>>=1; controlbits--;
		    }
		    *p_dst_len=p_dst-p_dst_first;
}
