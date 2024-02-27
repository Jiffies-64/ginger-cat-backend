from flask import Blueprint, request, jsonify
from paddleocr import PaddleOCR

OCRController = Blueprint('OCRController', __name__)

ocr = PaddleOCR(use_angle_cls=True, lang="ch")


@OCRController.route('/ocr', methods=['GET'])
def ocr_by_url():
    image_url = request.args.get("imageUrl")
    ocr_result = ocr.ocr(image_url, cls=True)
    result = list()
    for idx in range(len(ocr_result)):
        res = ocr_result[idx]
        for line in res:
            points_lst, content = line[0], line[1][0]
            new_line = {
                "content": content,
                "pointList": points_lst
            }
            result.append(new_line)
    return jsonify(result)
