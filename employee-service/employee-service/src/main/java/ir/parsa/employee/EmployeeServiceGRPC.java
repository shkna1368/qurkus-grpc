package ir.parsa.employee;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
@GrpcService
public class EmployeeServiceGRPC extends GEmployeeServiceGrpc.GEmployeeServiceImplBase {
//public class EmployeeServiceGRPC implements GEmployeeService {
    @Override
    @Blocking
    public void getEmployee(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {

        String nationalCode= request.getNationalCode();
        Employee emp=  Employee.findByNationalCode(nationalCode);
        System.out.println(emp.toString());
        System.out.println("1");
        responseObserver.onNext(EmployeeResponse.newBuilder().
                        setId(emp.id)
                        .setFamily(emp.family)
                        .setNationalCode(emp.nationalCode)
                        .setName(emp.name)
                        .build());

        responseObserver.onCompleted();
    }

  /*@Override
    public Uni<EmployeeResponse> getEmployee(EmployeeRequest request) {


       String nationalCode= request.getNationalCode();
     Employee emp=  Employee.findByNationalCode(nationalCode);


    return Uni.createFrom().item(() ->


      EmployeeResponse.newBuilder().
        setId(emp.id)
        .setFamily(emp.family)
        .setNationalCode(emp.nationalCode)
        .setName(emp.name)
        .build()


    );

    }*/
}
