from flask import Blueprint, request, jsonify
from service.cal_senti import cal_senti
import jpype

SentiController = Blueprint('SentiController', __name__)


@SentiController.route('/senti', methods=['GET'])
def calculate_senti():
    text = request.args.get("text")
    result = cal_senti(text)
    return jsonify(result)

