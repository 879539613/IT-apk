#include<stdio.h>
#include<math.h>
#include<time.h>
#include <stdlib.h>
float calculate(int i,float date1,float date2);
int jisuan();
char change(int i);
void main()
{
	int counter=0,n,Y=0,Z;
	float last;
	printf("请输入四则运算题目数：");
	scanf("%d",&n);
	do{
		Z=jisuan();
		counter++;/*对计算条数的加一*/
		if(Z==1)
		{
			Y++;
		}
	}while(counter<n);
    last=(float)Y/(float)n*100;
	printf("你答对的题目条数为：%d。\n准确值：%.1f％。\n",Y,last);//对最后结果的输出
        system("pause");
}
int jisuan()
{


	int date1,date2,date3;//date1,date2，date3,是随机变量
	float y,z;//为date1,date2,date3进行运算服务
	int c,f;
	int Y=0;
	//c为第一个运算符随机性服务,f为第二个运算符随机性服务,Y正确条数
	float e,d;//用户输入值为e，系统运算结果为d
	char op1,op2;
        //优先级判断
	do{
			srand(time(NULL));//使date1,date2,date3，c,f输出的是随机数
			date1 = rand() % 100 + 1;
			date2 = rand() % 100 + 1;
			date3 = rand() % 100 + 1;
			c = rand() % 4+1 ;
			f = rand() % 4+1 ;

          
           if(c>2){//先判断第一个运算符是否乘，除
			y=(float)date1;
			z=(float)date2;
			d=calculate(c,y,z);
			y=d;
			z=(float)date3;
			d=calculate(f,y,z);
			}
			else
			{
				if(f>2)//再判断第二个运算符是否乘，除
				{
				y=(float)date2;
				    z=(float)date3;
			    	d=calculate(f,y,z);
				    y=(float)date1;
				    z=d;
					d=calculate(c,y,z);	
				}
				else
					{
					
                                    y=(float)date1;
				    z=(float)date2;
				    d=calculate(c,y,z);
					y=d;
				    z=(float)date3;
				    d=calculate(f,y,z);
				}
			}
	}while(d<0);

            op1=change(c);
	        op2=change(f);
			printf("%d %c %d %c %d=?(保留一位小数)\n",date1,op1,date2,op2,date3);
			scanf("%f",&e);

        //输入值判断
		if(fabs(e-d)>0.1)//为保持结果除法时的正确性用了绝对值fabs()，使结果精确到小数点后一位
			{

			    printf("正确答案：%.1f\n",d);
			
			}	
					
					else
					{
						Y++;
						printf("你答对了！\n");
					}   
					return Y;
					
}

float calculate(int i,float date1,float date2)
{
	float d;
	switch(i)
	{
	case 1:
	    d=date1+date2;
		break;
	case 2:
		d=date1-date2;
		break;
	case 3:
		d=date1*date2;
		break;
	case 4:
		d=date1/date2;
		break;
	}
	return d;
}
char change(int i)
{
	char op;
	switch(i)
	{
	case 1:
		op='+';
		break;
	case 2:
		op='-';
		break;
	case 3:
		op='*';
		break;
	case 4:
		op='/';
		break;
	}
	return op;
}