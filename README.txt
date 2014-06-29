NAME:
	SearchEngine
FUNCTION:
	A simple search engine.
VERAION:
	0.01
AUTHOR:
	jiangxin
Copyright (c) 2014, jiangxin


All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
Neither the name of the jiangxin nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

��־:
	Version 0.01
		��ɿ����ƣ�Ч�ʺ͹��ܶ��д��Ż���
	Version 0.02
		����һ�νϴ���޸ģ���Ҫ���������淽�棬���������Ը��󣬿�����ѡ���Ե���ȡ��ҳ��Ч�ʸ��ߣ�����Ҳ�������Ķ����������沿�ֺ�html����������ַ��롣��������ĸ����ӳ��������Խ�һ���½���ɾ���˲���������롣�����������ҳ��������Ч��bug�������֮ǰ�����еĲ������룬�����Ժ�Ľ�һ���޸ġ�
	Version 0.03
		������Webǰ�˲��֣����˴��룬ȥ����CSSֻ����Ч����ʽ���Ż��˽��棬���Ӽ�ࡣɾ���˲������õ���Դ������ͼƬ��
	Version 0.04
		�����û�����������������ַ����ķִʲ����ж������ࣨ������÷����ƥ���㷨��ʵ�ָ�ΪIKAnalyzerʵ�֣��Ӷ��õ����õ�Ч���������˴��롣ͬʱ��Ӧ�Ĳ������Դ�ļ�Ҳ�������޸ġ�
	Version 0.05
		�޸���Engine���ֵ��ش�bug�����������Ľ��ؼ���ȫ�����м�����Ȼ�󽫰�����ͬ�ؼ��ʵ��ı������Ӻϲ���ͬʱ�ı��������㷨���ܹ����Ч�ʡ�
	Version 0.06
		ȥ���˴���������룬�޸���һЩСbug�;��棬ȥ����wordtable.txt�ļ������ڱ�README�ļ��������ˡ���ʵ�ֺ����Ʋ��֡���
	Version 0.07
		ȥ����Ŀǰ�����Ѿ����ֵ�������룻���������в����ʵ��ļ��������������������ã��������ļ�������ļ�����ע�ͣ����ڶ��ļ�����������գ�������ʾ��Ϣ��������Ӻ��������ڽ�һ���ķ����о����Ѿ������ﵽ�γ����ձ�׼��
	Version 0.08
		ȥ�����������õĵ�����jar����Ŀǰ������httpclient��htmlparser��IKAnalyzer���ָ�����IKAnalyzer���İ汾��IKAnalyzer3.2.8���µ�IKAnalyzer2012_u6���д�Ч���������㡣
	Version 0.09
		HttpClient����Httpclient-3.0.1���µ����µ�httpcomponents-client-4.3.3������ԭ���Ǿɰ��Ѿ�ֹͣ���£��Ҵ���һЩ�������ԡ����ڰ汾��Խ���Ƚϴ󣬽ӿڱ仯�ϴ����Ա������е�Crawler.DownLoadFile.java�ļ��仯�ϴ�
	Version 0.10
		ʵ����CreateIndex�Ĵ���Ż�����ز���Ч����߽�200����
	Version 0.11
		ʵ����Crawler�Ķ��̲߳��У�Ч������Լ30%�������ڲ������̣߳�ͬ��������δ��ȫ������������Ҫ����ȡ100����ҳ����ʵ����ȡ����ҳ���ܲ���������100�������Ҿ������ȱ����ʵ�ʵ���������û�й�ϵ�ģ�û���˻��ں�����������һ����ҳ��
	Version 0.12
		����AllInOne�࣬���Խ����в���һ��ִ����ϡ�ͬʱ�޸��˸����ӳ�����д�ļ���Ŀ¼������ʱ�����쳣��bug��
	Version 0.13
		ʵ���˼򵥵�˫�ʽ������ѯ������Ӣ��&����ʵ�ֽ��������磺�й�&������Ĭ�������Ϊ��������
	Version 0.14
		ʵ���˼򵥵�˫�ʲ������ѯ������Ӣ��-����ʵ�ֲ�������磺�й�-������Ĭ�������Ϊ��������
	Version 0.15
		Ϊ�������ṩ������ѡ�ѡ���Ƿ�ɾ������ȡ�����ݣ��Ƿ��Զ�����ȡ��Ŀ��

��ʵ�ֺ����Ʋ��֣�
	1�������������ʼ��͸����ʾ�ı���
	3����ȡ���Լ��ķִʷ��������������ⷽ����
	4���޸���ʾ���ҳ��ؼ����ظ���bug��
	5���Ż������ʾ˳��
	7��ɾ��������롣
	8��ʵ�����������ҳ��
	9���Ż����ҳ�棬������ַ������ɫ��ʾ��ȥ������������
	10��ʵ��PageRank�����㷨��
	12���޸�ÿ�ν�����100�������bug��