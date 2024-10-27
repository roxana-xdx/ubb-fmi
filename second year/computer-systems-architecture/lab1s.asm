; Evaluate the expression z=((a+1)*(a+1)+2)^2/(b*b+c*c) for signed numbers

ASSUME cs:text_,ds:data_

data_ SEGMENT
a dw -3
b dw 2
c dw -1
z dw ?
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax
; Beginning of the program
mov ax, a ; ax = a
add ax, 1 ; ax = a+1
imul ax ; ax = (a+1)^2
add ax, 2 ; ax = (a+1)^2 + 2
imul ax ; ax = ((a+1)^2 + 2)^2
mov cx, ax ; the dividend
mov ax, b ; ax = b
imul ax ; ax = b*b
mov bx, ax ; bx = b*b
mov ax, c ; ax = c
imul ax ; ax = c*c
add ax, bx ; ax = b*b + c*c
mov bx, ax ; the divisor
mov ax, cx ; the dividend
cwd
idiv bx
mov z, ax ; the result of the expression

; End of the program
mov ax, 4c00h
int 21h
text_ ENDS

END start

; use case: a=-3,b=2,c=-1--> ax = 7, dx = 1