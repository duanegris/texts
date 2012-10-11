struct message {
	int cli_id;
	string message<>;
      int msg_id;
};

typedef struct message message;
typedef message msg_list<99>;

program CHAT{
  version VERSION_ONE{
    void CHAT_NULL(void) = 0;
    int CONNECT() = 1;
    int DISCONNECT(int) = 2;
    int SEND(message) = 3;
    msg_list REFRESH(int) = 4; 
  } = 1;
} = 0x20000001;
