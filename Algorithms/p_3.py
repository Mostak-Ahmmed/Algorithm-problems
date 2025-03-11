# File read and write 
str=input("Enter first name: ")
print(str, file=open('Testfile.txt', 'w'))
str=input("Enter last name: ")
print(str,file=open('Testfile.txt','a'))