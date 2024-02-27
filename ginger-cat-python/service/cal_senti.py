import jpype

init = False
sentistrength = None


def cal_senti(body):
    global init
    global sentistrength

    if not init:
        Sentistrength = jpype.JClass("uk.ac.wlv.sentistrength.SentiStrength")
        # 创建对象并调用方法
        sentistrength = Sentistrength()
        init = True

    result = sentistrength.initialiseAndRun(['sentidata', "sentistrength/SentiStrength_Data/", "text", body])

    return str(result).split()
