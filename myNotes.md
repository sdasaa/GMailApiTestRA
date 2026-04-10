#####################################################################################
#                       Yaml file environment values                                #
#####################################################################################
1) Substitution
    Sustituting a value at compose parse time
    can be done by: .env file | ENV1=VAL1 docker compose up | docker compose --env-file custom-config.env up
2) Runtime env variable
    Setting env variables inside a container during runtime
    runner.yaml
        env_file:
        - ./custom-config.env
3) .env files are used for COMPOSE PARSE TIME - Variable SUBSTITUTION (i.e)
     .env
        MAPPED_VOL=/some/host/path
    runner.yaml
        //some service
        volumes:
        -${MAPPED_VOL}:/some/container/path
    > docker compose up
        *The variable will be substituted during parse time and resolved correctly /some/host/path:/some/container/path
        *But the same will not be set as env variable inside the container
4) Custom env files for setting env variables in containers during runtime - COMPOSE RUNTIME
    custom-config.env:
        BROWSER=firefox
    runner.yaml
        //some service
        env_file
        - ./custom-config.env
    > docker compose up
        * The variable will be set as env variable inside the container
5) Using .env file for both purpose - .env for both SUBSTITUTION & SETTING ENV VARIABLE
        .env
            MAPPED_VOL=/some/host/path
            BROWSER=firefox
        runner.yaml
        //some service
        environment:
        - BROWSER=${BROWSER}
        volumes:
        - ${MAPPED_VOL}:/some/container/path
6) Using cusom-config.env for both purpose - custom-config.env for both SUBSTITUTION & SETTING ENV VARIABLE
    custom-config.env
        BROWSER=firefox
        MAPPED_VOL=/some/host/path
    runner.yaml
        //some service
        env_file
        - ./cusom-config.env
        volumes:
        - ${MAPPED_VOL}:/some/container/path
    > docker compose --env-file ./custom-config.env
    

    
        