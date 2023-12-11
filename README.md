# Projeto-Individual-Bianca

## O que é a Solução MedConnect?
A Solução MedConnect surgiu como resposta aos desafios enfrentados pelas máquinas cirúrgicas Da Vinci, da Intuitive Surgical, relacionados à instabilidade de hardware. Nosso compromisso primordial é proporcionar segurança às cirurgias minuciosas conduzidas por esse dispositivo. Em um cenário em que o mercado tecnológico cresce de maneira constante, abrangendo setores cruciais como o hospitalar, as cirurgias robóticas emergiram como uma contribuição significativa, salvando inúmeras vidas ao redor do mundo.

Ciente de relatos anteriores sobre falhas de hardware em robôs cirúrgicos, nossa missão é mitigar esses problemas, fomentando o crescimento desse mercado e, assim, preservar mais vidas.

## Qual o propósito do projeto?
O propósito central do projeto é incorporar à Solução MedConnect um novo método de monitoramento, concentrando-se nos processos da máquina e na correlação direta entre temperatura, CPU e a ocorrência de processos ativos.

## Qual a sua composição? 
O núcleo para a criação da nova dashboard de monitoramento consiste em uma API em Kotlin, orientada a objetos, que captura dados de CPU e temperatura da placa mãe, além de uma API em Python, responsável por registrar os processos ativos do sistema operacional

## O que é necessário para que este projeto funcione?
Para garantir a execução plena da página, é necessário executar o JAR relacionado ao código construído em Kotlin e também iniciar o código desenvolvido em Python. Ambos os códigos incorporam loops de execução, o que implica que, uma vez acionados, a captura de dados ocorrerá a intervalos regulares (dependendo da API correspondente). Dessa forma, os dados de hardware são capturados e enviados para um banco de dados na nuvem, especificamente o SQL Server. Esses dados são posteriormente transformados e exibidos nas dashboards do site institucional da MedConnect, acessível também via IP Elástico.

## Quais são as principais tecnologias utilizadas neste projeto?
<a href = ""> <img src="https://camo.githubusercontent.com/d63d473e728e20a286d22bb2226a7bf45a2b9ac6c72c59c0e61e9730bfe4168c/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f48544d4c352d4533344632363f7374796c653d666f722d7468652d6261646765266c6f676f3d68746d6c35266c6f676f436f6c6f723d7768697465">
<a href = ""> <img src = "https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white"> </a>
<a href = ""> <img src="https://camo.githubusercontent.com/93c855ae825c1757f3426f05a05f4949d3b786c5b22d0edb53143a9e8f8499f6/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4a6176615363726970742d3332333333303f7374796c653d666f722d7468652d6261646765266c6f676f3d6a617661736372697074266c6f676f436f6c6f723d463744463145">
<a href = ""> <img src="https://camo.githubusercontent.com/a4a4a017a5d519d7c4ce2a3cd3d2194fb7af4b1ca424850784565007c2acc7d8/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d7953514c2d3030354338343f7374796c653d666f722d7468652d6261646765266c6f676f3d6d7973716c266c6f676f436f6c6f723d7768697465">
<a href = ""> <img src = "https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54"> </a>
<a href = ""> <img src = "https://img.shields.io/badge/r-%23276DC3.svg?style=for-the-badge&logo=r&logoColor=white"> </a>
<a href = ""> <img src = "https://img.shields.io/badge/PowerShell-%235391FE.svg?style=for-the-badge&logo=powershell&logoColor=white"> </a>
<a href = ""> <img src = "https://img.shields.io/badge/chart.js-F5788D.svg?style=for-the-badge&logo=chart.js&logoColor=white"> </a>
<a href = ""> <img src = "https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white"> </a>
<a href = ""> <img src = "https://img.shields.io/badge/Trello-%23026AA7.svg?style=for-the-badge&logo=Trello&logoColor=white"> </a>
<a href = ""> <img src = "https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> </a>

## Conclusão
Em conclusão, o Projeto-Individual-Bianca representa um passo crucial na evolução da Solução MedConnect, fortalecendo a estabilidade das máquinas cirúrgicas Da Vinci. Ao introduzir um monitoramento mais abrangente e eficaz, estamos não apenas superando desafios técnicos, mas também contribuindo para a excelência e confiabilidade das cirurgias robóticas. Este projeto não apenas eleva os padrões de segurança, mas também solidifica a posição da MedConnect como líder inovadora no setor, impulsionando o avanço contínuo da medicina de precisão.



