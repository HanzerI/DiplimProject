import vk_api


def connectionTest(login,password):
        try:
            vk_session = vk_api.VkApi(login, password)
            vk_session.auth()
            tools = vk_api.VkTools(vk_session)
            print("Авторизация успешна")
            return tools
        except vk_api.AuthError as error_msg:
            print(error_msg)


def connection():
    while True:
        try:
            login, password = inputDataToLogIn()
            vk_session = vk_api.VkApi(login, password)
            vk_session.auth(token_only=True)
            tools = vk_api.VkTools(vk_session)
            return tools
        except vk_api.AuthError as error_msg:
            print(error_msg)


def inputNumPost():
    print("Введите количество постов для анализа")
    while True:
        try:
            count = int(input())
            break
        except ValueError:
            print("Вы ввели не число. Пооробуйте снова: ")
    if type(count) != int or count <= 0 or count > 10000:
        count = 100
    return count


def inputNumPostTest(count):
    try:
        count = int(count)
    except ValueError:
        print("Вы ввели не число. Пооробуйте снова: ")
    if type(count) != int or count <= 0 or count > 10000:
        count = 100
    return count


def inputDataToLogIn():
    print("Введите логин и пароль")
    while True:
        try:
            login = input()
            password = input()
            return login, password
        except ValueError:
            return


def analysisPosts():
    from dostoevsky.tokenization import RegexTokenizer
    from dostoevsky.models import FastTextSocialNetworkModel
    tokenizer = RegexTokenizer()
    model = FastTextSocialNetworkModel(tokenizer=tokenizer)
    tools = connection()
    count = inputNumPost()
    post_it = tools.get_all_slow_iter('newsfeed.search', count, {'q': '#Саратов', 'count': 1}, 'items', 1)
    for post in post_it:
        message = [post['text']]
        result = model.predict(message, k=2)
        print('=====================================================================')
        print(message, '\nОценка', result)
        print('=====================================================================')


# def testConnetion1():
#     # currectTools = connectionTest("+79379658586", "D1318565d")
#     res = connectionTest("ffsf", "1212")
#     # assert res == currectTools
#
#
#
# def testConnetion2():
#     res = connectionTest("+79379658586", "1212")
#     # currectTools = connectionTest("+79379658586", "D1318565d")
#     # assert res == currectTools
#
#
# def testConnetion3():
#     # currectTools = connectionTest("+79379658586", "D1318565d")
#     res = connectionTest("+79379658586", "drwr34431212")
#     # assert res == currectTools
#
#
# def testConnetion4():
#     # currectTools = connectionTest("+79379658586", "D1318565d")
#     res = connectionTest("", "drwr34431212")
#     # assert res == currectTools
#
#
# def testConnetion5():
#     # currectTools = connectionTest("+79379658586", "D1318565d")
#     res = connectionTest("+79379658586", "D1318565d")
#     # assert res == currectTools
#
#

def testCount1():
    count = inputNumPostTest(10)
    assert count == 10

def testCount2():
    count = inputNumPostTest("fdssd")
    assert count == 100

def testCount3():
    count = inputNumPostTest("")
    assert count == 100

def testCount4():
    count = inputNumPostTest(100000)
    assert count == 100

def testCount5():
    count = inputNumPostTest(-10)
    assert count == 100


def main():
    # analysisPosts()
    print("Hi")
    # testConnetion1()
    # testConnetion2()
    # testConnetion3()
    # testConnetion4()
    # testConnetion5()


if __name__ == '__main__':
    main()
