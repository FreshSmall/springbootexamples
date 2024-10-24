package com.example.springbootwebsimple.grpc;

import com.baijia.digit.service.GetMediaAccountRequest;
import com.baijia.digit.service.GetMediaAccountResponse;
import com.baijia.digit.service.ListMediaAccountRequest;
import com.baijia.digit.service.ListMediaAccountResponse;
import com.baijia.digit.service.MediaAccountServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author: yinchao
 * @ClassName: MediaGrpcServiceImpl
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/8/14 09:55
 */
@GrpcService
public class MediaGrpcServiceImpl extends MediaAccountServiceGrpc.MediaAccountServiceImplBase {

    @Override
    public void getMediaAccount(GetMediaAccountRequest request, StreamObserver<GetMediaAccountResponse> responseObserver) {
        GetMediaAccountResponse.Builder builder = GetMediaAccountResponse.newBuilder();
        builder.setAccountId("test01");
        builder.setAccountManagerId("123938");
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
