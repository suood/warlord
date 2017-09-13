//package com.suood.tensor;
//
//import org.tensorflow.Session;
//
///**
// * Created by FENGCUIJIE on 2017/4/6.
// */
//public class Firs {
////FIXME BP神经网络。
//    public static void main(String[] args) {
//        final Session session = new Session(new SessionOptions());
//        GraphDef def = new GraphDef();
//        tensorflow.ReadBinaryProto(Env.Default(),
//                "somedir/trained_model.proto", def);
//        Status s = session.Create(def);
//        if (!s.ok()) {
//            throw new RuntimeException(s.error_message().getString());
//        }
//    }
//}
