package com.cosmos.test;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 22:25
 * @Modified By：
 */
public class ProtoBufTest {

//    private byte[] endcode(RPCRequestProto.RPCRequest rpcRequest){
//        return rpcRequest.toByteArray();
//    }
//
//    private RPCRequestProto.RPCRequest decode(byte[] body) throws InvalidProtocolBufferException {
//        return RPCRequestProto.RPCRequest.parseFrom(body);
//    }
//
//    private RPCRequestProto.RPCRequest createRPCRequest(){
//        RPCRequestProto.RPCRequest.Builder builder= RPCRequestProto.RPCRequest.newBuilder();
//        builder.setRequestId("23333");
//        builder.setMethodName("test");
//        builder.setClassName("class");
//        return builder.build();
//    }
//
//    @Test
//    public void testProtoBuf() throws InvalidProtocolBufferException {
//        RPCRequestProto.RPCRequest rpcRequest=createRPCRequest();
//        System.out.println("解码前:"+rpcRequest.toString());
//        System.out.println(endcode(rpcRequest).toString());
//        RPCRequestProto.RPCRequest rpcRequest2 =decode(endcode(rpcRequest));
//        System.out.println("解码后重新编码:"+rpcRequest.toString());
//        System.out.println("是否相等:"+rpcRequest2.equals(rpcRequest));
//    }

}
