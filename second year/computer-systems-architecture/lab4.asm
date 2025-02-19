; 9. Se da un sir de litere mari si mici. Sa se afiseze literele mari pe ecran si sa se afiseze numarul acestor litere pe ecran.

ASSUME cs:text_,ds:data_

data_ SEGMENT
sir db 15, ?, 15 dup(?)
newline db 0Dh, 0Ah, '$'
zece dw 10
data_ ENDS

text_ SEGMENT

start:
mov ax, data_
mov ds, ax

; Inceputul programului
mov ah, 0Ah ; citim un sir de la tastatura
mov dx, offset sir
int 21h

mov cl, sir[1]
mov ch, 0 ; in cx avem lungimea sirului

mov bl, 0 ; aici vom pastra numarul de litere mari
mov si, 2 ; indexul primei litere din sir

repeta:
mov bh, sir[si]
cmp bh, 65 ; comparam litera de la indexul si cu A
jge maimare
jl urmatoarea

maimare:
cmp bh, 90 ; comparam litera de la indexul si cu Z
jle afiseaza
jg urmatoarea

afiseaza:
inc bl

mov ah, 09h
lea dx, newline
int 21h

mov dl, bh
mov ah, 02h
int 21h

urmatoarea:
inc si

loop repeta

; afisare numar de litere mari
mov ah, 09h
lea dx, newline
int 21h

mov si, 0 ; numarul de cifre care trebuie afisate
mov al, bl
mov ah, 0 ; ax = bl

accesare_cifre:
cwd ; dx:ax = nr de litere
div zece ; dx = rest
push dx
add si, 1
cmp ax, 0
jne accesare_cifre
je afisare

afisare:
mov cx, si

repeta_afisare:
pop dx ; dl contine cifra
add dl, '0'
mov ah, 02h
int 21h
loop repeta_afisare

; Terminarea programului
mov ax, 4c00h
int 21h
text_ ENDS

END start