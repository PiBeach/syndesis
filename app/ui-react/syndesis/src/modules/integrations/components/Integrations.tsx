import { getLastStep, getStartStep } from '@syndesis/api';
import { IntegrationWithMonitoring } from '@syndesis/models';
import {
  IntegrationActions,
  IntegrationsList,
  IntegrationsListItem,
  IntegrationsListItemUnreadable,
  IntegrationsListSkeleton,
} from '@syndesis/ui';
import { WithLoader } from '@syndesis/utils';
import * as React from 'react';
import { Translation } from 'react-i18next';
import { AppContext } from '../../../app';
import { ApiError, EntityIcon } from '../../../shared';
import resolvers from '../resolvers';
import { WithIntegrationActions } from './WithIntegrationActions';

export interface IIntegrationsProps {
  error: boolean;
  errorMessage?: string;
  loading: boolean;
  integrations: IntegrationWithMonitoring[];
}

export class Integrations extends React.Component<IIntegrationsProps> {
  public render() {
    return (
      <Translation ns={['integrations', 'shared']}>
        {t => (
          <AppContext.Consumer>
            {({ getPodLogUrl }) => (
              <IntegrationsList>
                <WithLoader
                  error={this.props.error}
                  loading={this.props.loading}
                  loaderChildren={<IntegrationsListSkeleton />}
                  errorChildren={<ApiError error={this.props.errorMessage!} />}
                >
                  {() =>
                    this.props.integrations.map(
                      (mi: IntegrationWithMonitoring) => {
                        try {
                          const startStep = getStartStep(
                            mi.integration,
                            mi.integration.flows![0].id!
                          );
                          const endStep = getLastStep(
                            mi.integration,
                            mi.integration.flows![0].id!
                          );
                          return (
                            <WithIntegrationActions
                              key={mi.integration.id}
                              integration={mi.integration}
                            >
                              {({ actions }) => (
                                <IntegrationsListItem
                                  integrationName={mi.integration.name}
                                  currentState={mi.integration!.currentState!}
                                  targetState={mi.integration!.targetState!}
                                  isConfigurationRequired={
                                    !!(
                                      mi.integration!.board!.warnings ||
                                      mi.integration!.board!.errors ||
                                      mi.integration!.board!.notices
                                    )
                                  }
                                  monitoringValue={
                                    mi.monitoring &&
                                    t(
                                      'integrations:' +
                                        mi.monitoring.detailedState.value
                                    )
                                  }
                                  monitoringCurrentStep={
                                    mi.monitoring &&
                                    mi.monitoring.detailedState.currentStep
                                  }
                                  monitoringTotalSteps={
                                    mi.monitoring &&
                                    mi.monitoring.detailedState.totalSteps
                                  }
                                  monitoringLogUrl={getPodLogUrl(mi.monitoring)}
                                  startConnectionIcon={
                                    <EntityIcon
                                      entity={startStep}
                                      alt={startStep!.name || 'Step'}
                                      className={'integration-icon__icon'}
                                    />
                                  }
                                  finishConnectionIcon={
                                    <EntityIcon
                                      entity={endStep}
                                      alt={endStep!.name || 'Step'}
                                      className={'integration-icon__icon'}
                                    />
                                  }
                                  actions={
                                    <IntegrationActions
                                      integrationId={mi.integration!.id!}
                                      actions={actions}
                                      detailsHref={resolvers.integration.details(
                                        {
                                          integrationId: mi.integration.id!,
                                        }
                                      )}
                                    />
                                  }
                                  i18nConfigurationRequired={t(
                                    'integrations:ConfigurationRequired'
                                  )}
                                  i18nError={t('shared:Error')}
                                  i18nPublished={t('shared:Published')}
                                  i18nUnpublished={t('shared:Unpublished')}
                                  i18nProgressPending={t('shared:Pending')}
                                  i18nProgressStarting={t(
                                    'integrations:progressStarting'
                                  )}
                                  i18nProgressStopping={t(
                                    'integrations:progressStopping'
                                  )}
                                  i18nLogUrlText={t('shared:viewLogs')}
                                />
                              )}
                            </WithIntegrationActions>
                          );
                        } catch (e) {
                          return (
                            <IntegrationsListItemUnreadable
                              key={mi.integration.id}
                              integrationName={
                                (mi && mi.integration && mi.integration.name) ||
                                'An integration'
                              }
                              i18nDescription={
                                "Sorry, we can't display more information about this integration right now."
                              }
                              rawObject={JSON.stringify(
                                mi.integration,
                                null,
                                2
                              )}
                            />
                          );
                        }
                      }
                    )
                  }
                </WithLoader>
              </IntegrationsList>
            )}
          </AppContext.Consumer>
        )}
      </Translation>
    );
  }
}
