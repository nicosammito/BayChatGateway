package net.gymbay.baychat.manager;

import net.gymbay.baychat.receive.ReceiveHello;
import net.gymbay.baychat.receive.ReceiveIdentify;
import net.gymbay.baychat.send.SendHello;

public enum ClientOperation {

    HELLO(1, new ReceiveHello(), new SendHello()),
    IDENTIFY(2,new ReceiveIdentify(), null);


    private final Integer opcode;
    private final ReceiveHandler receiveHandler;
    private final SendHandler sendHandler;
    ClientOperation(final Integer opcode, final ReceiveHandler receiveHandler, final SendHandler sendHandler) {
        this.opcode = opcode;
        this.receiveHandler = receiveHandler;
        this.sendHandler = sendHandler;
    }

    public Integer getOpcode() {
        return opcode;
    }

    public ReceiveHandler getReceiveHandler() {
        return receiveHandler;
    }

    public SendHandler getSendHandler() {
        return sendHandler;
    }

    public static ClientOperation getClientOperation(int opCode){

       for(ClientOperation clientOperation : values()){
          if(opCode == clientOperation.getOpcode()){
              return clientOperation;
          }
       }

       return null;

    }

}
