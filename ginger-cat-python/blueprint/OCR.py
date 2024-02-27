from flask import Blueprint, request, jsonify
from paddleocr import PaddleOCR

OCRController = Blueprint('OCRController', __name__)

ocr = PaddleOCR(use_angle_cls=True, lang="ch")


@OCRController.route('/ocr', methods=['POST'])
def ocr_by_url():
    image_url = request.json.get("imageUrl")
    result = ocr.ocr(image_url, cls=True)
    for idx in range(len(result)):
        res = result[idx]
        for line in res:
            print(line)
    return jsonify({"result": result})
