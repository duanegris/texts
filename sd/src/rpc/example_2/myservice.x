struct data {
  unsigned int arg1;
  unsigned int arg2;
};

typedef struct data data;

struct response {
  unsigned int result;
  unsigned char error;
};

typedef struct response response;

program MYCOMPUTATION {
  version VERSION_ONE{
    void MYCOMPUTATION_NULL(void) = 0;
    response MYCOMPUTATION_ADDITION(data) = 1;
  } = 1;
} = 0x20000001;
