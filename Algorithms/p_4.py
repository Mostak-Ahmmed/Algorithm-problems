# print(file=open('Testfile.txt','r'))

with open('Testfile.txt', 'r') as file:
    file_contents = file.read()
    print(file_contents)
