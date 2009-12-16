/* compress.h */

#define UBYTE unsigned char /* Unsigned byte (1 byte ) */
#define UWORD unsigned int /* Unsigned word (2 bytes) */
#define ULONG unsigned long /* Unsigned longword (4 bytes) */
#define FLAG_BYTES 4 /* Number of bytes used by copy flag. */
#define FLAG_COMPRESS 0 /* Signals that compression occurred. */
#define FLAG_COPY 1 /* Signals that a copyover occurred. */
void fast_copy(UBYTE *p_src ,UBYTE *p_dst, int len) /* Fast copy routine. */
	  { while (len--) *p_dst++=*p_src++; }
#define FALSE 0
#define TRUE 1
