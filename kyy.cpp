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
	printf("����������������Ŀ����");
	scanf("%d",&n);
	do{
		Z=jisuan();
		counter++;/*�Լ��������ļ�һ*/
		if(Z==1)
		{
			Y++;
		}
	}while(counter<n);
    last=(float)Y/(float)n*100;
	printf("���Ե���Ŀ����Ϊ��%d��\n׼ȷֵ��%.1f����\n",Y,last);//������������
        system("pause");
}
int jisuan()
{


	int date1,date2,date3;//date1,date2��date3,���������
	float y,z;//Ϊdate1,date2,date3�����������
	int c,f;
	int Y=0;
	//cΪ��һ�����������Է���,fΪ�ڶ������������Է���,Y��ȷ����
	float e,d;//�û�����ֵΪe��ϵͳ������Ϊd
	char op1,op2;
        //���ȼ��ж�
	do{
			srand(time(NULL));//ʹdate1,date2,date3��c,f������������
			date1 = rand() % 100 + 1;
			date2 = rand() % 100 + 1;
			date3 = rand() % 100 + 1;
			c = rand() % 4+1 ;
			f = rand() % 4+1 ;

          
           if(c>2){//���жϵ�һ��������Ƿ�ˣ���
			y=(float)date1;
			z=(float)date2;
			d=calculate(c,y,z);
			y=d;
			z=(float)date3;
			d=calculate(f,y,z);
			}
			else
			{
				if(f>2)//���жϵڶ���������Ƿ�ˣ���
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
			printf("%d %c %d %c %d=?(����һλС��)\n",date1,op1,date2,op2,date3);
			scanf("%f",&e);

        //����ֵ�ж�
		if(fabs(e-d)>0.1)//Ϊ���ֽ������ʱ����ȷ�����˾���ֵfabs()��ʹ�����ȷ��С�����һλ
			{

			    printf("��ȷ�𰸣�%.1f\n",d);
			
			}	
					
					else
					{
						Y++;
						printf("�����ˣ�\n");
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