CC=gcc

all: dict
	$(CC) `pkg-config --cflags libsoup-2.2` dict.c `pkg-config --libs libsoup-2.2` -o dict
