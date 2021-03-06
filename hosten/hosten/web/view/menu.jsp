<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<nav class="white">
    <div class="nav-wrapper container">
        <a href="#" class="brand-logo center">hosten</a>
        <ul class="left">
            <li><a id="button-menu" data-activates="slide-out"><i class="material-icons">menu</i></a></li>
        </ul>
    </div>
</nav>

<ul id="slide-out" class="side-nav">
    <li>
        <div class="user-view">
            <div class="background">
                <img src="../imgs/sidenav-background.jpg"/>
            </div>
        </div>
    <li>

    <li><a class="collapsible-header waves-effect" href="/hosten/servletweb?acao=ListarCargos&tipoAcao=Padrao"><i class="material-icons grey-text text-darken-3">business_center</i>Cargos</a></li>
    <li><a class="collapsible-header waves-effect" href="/hosten/servletweb?acao=ListarUsuarios"><i class="material-icons grey-text text-darken-3">assignment_ind</i>Funcionários</a></li>
    <li><a class="collapsible-header waves-effect" href="/hosten/servletweb?acao=ListarHospedes"><i class="material-icons grey-text text-darken-3">people</i>Hóspedes</a></li>
    
    <li class="no-padding">
        <ul class="collapsible collapsible-accordion">
            <li>
                <a class="collapsible-header waves-effect" href="#"><i class="material-icons grey-text text-darken-3">local_hotel</i>Quartos<i class="material-icons right dropdown-icon">arrow_drop_down</i></a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="/hosten/servletweb?acao=ListarQuartos"><i class="material-icons">search</i>Visualizar quartos</a></li>
                        <li><a href="/hosten/servletweb?acao=ListarQuartosEstados"><i class="material-icons">assignment</i>Estados dos quartos</a></li>
                        <li><a href="/hosten/servletweb?acao=ListarCategoriasQuarto"><i class="material-icons">local_offer</i>Categorias de quartos</a></li>
                        <li><a href="/hosten/servletweb?acao=ListarItensConforto&tipoAcao=Padrao"><i class="material-icons">stars</i>Itens de conforto</a></li>
                    </ul>
              </div>
            </li>
        </ul>
    </li>

    <li class="no-padding">
        <ul class="collapsible collapsible-accordion">
            <li>
                <a class="collapsible-header waves-effect" href="#"><i class="material-icons grey-text text-darken-3">room_service</i>Serviços<i class="material-icons right dropdown-icon">arrow_drop_down</i></a>
                <div class="collapsible-body">
                    <ul>
                        <li><a href="/hosten/servletweb?acao=ListarServicos"><i class="material-icons">search</i>Visualizar serviços</a></li>
                        <li><a href="/hosten/servletweb?acao=ListarServicoAreas&tipoAcao=Padrao"><i class="material-icons">search</i>Visualizar áreas de serviço</a></li>
                    </ul>
                </div>
            </li>
        </ul>
    </li>

    <li><a class="collapsible-header waves-effect" href="/hosten/servletweb?acao=Logout"><i class="material-icons grey-text text-darken-3">exit_to_app</i>Logout</a></li>
</ul>
