from flask import Flask
from flask import request ,jsonify 

from kafka import KafkaProducer

import json

# from service.messageService import messageService

# from service.messageService import MessageService
from app.service.messageService import MessageService



app = Flask(__name__)
app.config.from_pyfile('config.py')

messageService = MessageService()
producer = KafkaProducer(bootstrap_servers=['localhost:9092'],value_serializer = lambda v: json.dumps(v).encode('utf-8'))

@app.route('/v1/message/', methods=['POST'])
def handle_message():
    message = request.json.get('message')
    result = messageService.process_message(message)

    if result is None:
        return jsonify({"error": "Not a valid bank message"}), 400

    # No need to call .json(), result is already a dict
    producer.send("data-science-topic", value=result)

    return jsonify(result)


@app.route('/',methods=['GET'])
def handle_get():
    print("Hello world")


if __name__=="__main__":
    app.run(host="localhost",port=8000,debug=True)
