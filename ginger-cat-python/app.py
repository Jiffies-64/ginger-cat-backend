import jpype
from flask import Flask
from blueprint.OCR import OCRController
from blueprint.Senti import SentiController

# import threading
# import nacos
#
# SERVER_ADDRESSES = "121.41.224.122:8848"
# SERVER_NAMESPACE = "gc-dev"
# SERVER_NAME = "ocr-service"
# client = nacos.NacosClient(server_addresses=SERVER_ADDRESSES, namespace=SERVER_NAMESPACE)
#
#
# def service_register():
#     """
#      ephemeral参数：是否是临时服务，应为false;
#      刚才上面也提到了，如果是 非临时实例，客户端就无需主动完成心跳检测。
#      因此此处将服务注册为 非临时实例
#     """
#     client.add_naming_instance(SERVER_NAME, "127.0.0.1", "63020", ephemeral=False)

jpype.startJVM(classpath="sentistrength/SentiStrength-1.0-SNAPSHOT.jar")

app = Flask(__name__)
app.register_blueprint(OCRController, url_prefix='/api')
app.register_blueprint(SentiController, url_prefix='/api')

if __name__ == '__main__':
    # threading.Timer(5, service_register).start()
    app.run()
